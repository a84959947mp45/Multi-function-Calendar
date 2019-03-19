
import javax.sound.sampled.*;   
import java.io.*;   
import java.net.*;   

public class AudioPlayer{   
    private AudioInputStream currentSound;   

    private Clip clip;   

    private float gain;
    private FloatControl gainControl;

    //�����n�D,-1.0f:�u�����n�D, 0.0f:���n�D,1.0f�k�n�D
    private float pan;
    private FloatControl panControl;  

    //�����R�� �}/��
    private boolean mute;
    private BooleanControl muteControl;

    //���񦸼�,�p�󵥩�0:�L��������,�j��0:���񦸼�
    private int playCount;

    private DataLine.Info dlInfo;
    private Object loadReference;   
    private AudioFormat format;

    //���ּ��񧹲��ɡA�Y���]�w�^call����H�A�h�|�q������H
    private AudioPlayerCallback callbackTartet;
    private Object callbackObj ;
    private boolean isPause;

    public AudioPlayer(){   
        AudioPlayerInit();
    }

    public void AudioPlayerInit(){
        currentSound = null;
        clip = null;
        gain = 0.5f;
        gainControl = null;  
        pan = 0.0f;
        panControl = null;
        mute = false;
        muteControl = null;
        playCount = 0;
        dlInfo = null;
        isPause = false;
    }

    /**
     * �]�w�n�������ּ��񧹮ɨƥ󪺹�H
     * @param cb    ����callback����H
     * @param obj    callback�^�Ӫ�����
     */
    public void setCallbackTartet(AudioPlayerCallback cb, Object obj){
        callbackTartet = cb;
        callbackObj = obj;
    }

    /**
     * �]�w���񦸼�,���񦸼�,�p�󵥩�0:�L��������,�j��0:���񦸼�
     * @param c
     */
    public void setPlayCount(int c){
        if(c < -1){
            c = -1;
        }
        playCount = c - 1;
    }

    /**
     * ���w���|Ū������,�^��true:���񦨥\,false:���񥢱�
     * @param filePath
     * @param obj �ثe�����m��package���|
     */
    public boolean loadAudio(String filePath){
        try {
            loadAudio(new File(filePath));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ���w���|Ū������,�ϥΥثe�����m��package��۹���|root,null�ɤ��ϥΪ�����|��root
     * @param filePath
     * @param obj �ثe�����m��package���|
     * @return �^��true:���񦨥\,false:���񥢱�
     */
    public boolean loadAudio(String filePath, Object obj){
        try {
            if(obj != null){
                loadAudio(obj.getClass().getResourceAsStream(filePath));
            }else{
                loadAudio(new File(filePath));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**  
     * �q����Ū������
     */   
    public void loadAudio(URL url) throws Exception{   
        loadReference = url;    
        currentSound = AudioSystem.getAudioInputStream(url);    
        finishLoadingAudio();    
    }   

    /**
     * Ū�����a�ݭ���
     * @param file
     * @throws Exception
     */
    public void loadAudio(File file) throws Exception{   
        loadReference = file;    
        currentSound = AudioSystem.getAudioInputStream(file);    
        finishLoadingAudio();    
    }   

    /**
     * �q��yŪ������
     * @param iStream
     * @throws Exception
     */
    public void loadAudio(InputStream iStream) throws Exception{   
        loadReference = iStream;    

        if (iStream instanceof AudioInputStream){   
            currentSound = (AudioInputStream)iStream;   
        } else { 
           //2014-02-02 �ץ�bug:���ɥ]�˦bjar�ɸ̮ɵL�k����
            InputStream bufferedIn = new BufferedInputStream(iStream);
            currentSound = AudioSystem.getAudioInputStream(bufferedIn); 
        }   
        finishLoadingAudio();    
    }   

    /**  
     * load�����ɫ�A�i�漽��]�w
     */   
    protected void finishLoadingAudio() throws Exception {   
        format = currentSound.getFormat();   
        dlInfo = new DataLine.Info(Clip.class, format, ((int) currentSound.getFrameLength() * format.getFrameSize()));   
        clip = (Clip) AudioSystem.getLine(dlInfo);   
        clip.open(currentSound);   
        clip.addLineListener(   
                new LineListener() {   
                    public void update(LineEvent event) {
                        if (event.getType().equals(LineEvent.Type.STOP)){
                            if(!isPause){
                                if(callbackTartet != null){
                                    callbackTartet.audioPlayEnd(callbackObj);
                                }
                                close();
                            }
                        }   
                    }   
                }   
        );   
    }

    /**
     * ������
     */
    public void play(){
        if(clip != null){
            clip.setFramePosition(0);  
            clip.loop(playCount);
        }
    }

    /**
     * ��_������
     *
     */
    public void resume(){
        isPause = false;
        
        if(clip != null){
            clip.setFramePosition(clip.getFramePosition());
            clip.loop(playCount);
        }
        
    }

    /**
     * �Ȱ�������
     */
    public void pause(){
        isPause = true;
        if(clip != null){
            clip.stop();
        }
    }

    /**
     * �������,�B�N���ɼ����m���^�}�l�B
     */
    public void stop(){
        if(clip != null){
            clip.stop();   
        }
    }   

    /**
     * �]�w���q
     * @param dB 0~1,�w�]��0.5
     */
    public void setVolume(float dB){
        float tempB = floor_pow(dB,1);
        //System.out.println("�ثe���q+"+tempB);
        gain = tempB;   
        resetVolume();

    }

    /**
     * @param min �n�L����˥h���Ʀr
     * @param Num �n�˥h�����
     *
     */
    private float floor_pow(float min, int Num){
        float n = (float)Math.pow(10, Num);
        float tmp_Num = ((int)(min*n))/n;
        return tmp_Num ;
    }

    /**
     * ���]���q
     */
    protected void resetVolume(){
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        //double gain = .5D; // number between 0 and 1 (loudest)
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }   

    /**
     * �]�w�n�D,-1.0f:�u�����n�D, 0.0f:���n�D,1.0f�k�n�D
     * @param p
     */
    public void setPan(float p){   
        pan = p;   
        resetPan();   
    }   

    /**
     * ���]�����D�B���n�D
     */
    protected void resetPan(){   
        panControl = (FloatControl) clip.getControl(FloatControl.Type.PAN);   
        panControl.setValue(this.pan);   
    }

    /**
     * �]�w�R�����A,true:�R��,false:���R��
     * @param m
     */
    public void setMute(boolean m){
        mute  = m;
        resetMute();
    }

    /**
     * ���]�R�����A
     *
     */
    protected void resetMute(){
        muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
        muteControl.setValue(mute);
    }

    /**
     *
     * @return
     */
    public int getFramePosition(){   
        try {   
            return clip.getFramePosition();   
        } catch (Exception e) {   
            return -1;   
        }   
    }   

    /**
     * ���o���ɮ榡
     * @return
     */
    public AudioFormat getCurrentFormat(){   
        return format;   
    }   

    /**
     * ���o���ɪ���y
     * @return
     */
    public AudioInputStream getAudioInputStream(){   
        try {   
            AudioInputStream aiStream;   


            if (loadReference == null){   
                return null;   
            } else if (loadReference instanceof URL) {   
                URL url = (URL)loadReference;   
                aiStream = AudioSystem.getAudioInputStream(url);   
            } else if (loadReference instanceof File) {   
                File file = (File)loadReference;   
                aiStream = AudioSystem.getAudioInputStream(file);   
            } else if (loadReference instanceof AudioInputStream){   
                AudioInputStream stream = (AudioInputStream)loadReference;   
                aiStream = AudioSystem.getAudioInputStream(stream.getFormat(), stream);   
                stream.reset();   
            } else {   

                InputStream inputStream = (InputStream)loadReference;   
                aiStream = AudioSystem.getAudioInputStream(inputStream);   
            }   

            return aiStream;   
        } catch (Exception e) {   
            e.printStackTrace();   
            return null;   
        }   
    }   


    /**
     * �ثe���ɬO�_�w�s�b
     * @return
     */
    public boolean isAudioLoaded(){   
        return loadReference!= null;   
    }   

    /**
     * ���o�ſ譵��
     * @return
     */
    public Clip getClip() {   
        return clip;   
    }

    /**  
     * ��������
     */   
    public void close(){   
        try {   
            if (clip != null)   
                clip.close();   
            if (currentSound != null)   
                currentSound.close();   
            loadReference = null;   
        } catch (Exception e){   
            //System.out.println("unloadAudio: " + e);   
            e.printStackTrace();   
        }
        
        currentSound = null;   
        clip = null;   
        gainControl = null;   
        panControl = null;   
        dlInfo = null;   
        loadReference = null;
        muteControl = null;
        callbackTartet = null;
        callbackObj = null;
    }


    public static void main(String [] args){

    }
}