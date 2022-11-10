package streaming_music;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Play_Song
    {
        Long currentFrame;                            // to store current position
        Clip clip;
        String status;                          // current status of clip
        AudioInputStream audioInputStream;
        static String filePath;

        public Play_Song(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());             // create AudioInputStream object
            clip = AudioSystem.getClip();                                                // create clip reference
            clip.open(audioInputStream);                           // open audioInputStream to the clip
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        public static void playSong(String path)
        {
            try {

                Play_Song audioPlayer = new Play_Song(path);

                audioPlayer.play();
                Scanner sc = new Scanner(System.in);

                while (true)
                {
                    System.out.println(" PRESS\n1. Pause the Song ⏸");
                    System.out.println("2. Resume the Song ⏯");
                    System.out.println("3. Restart         ▶️");
                    System.out.println("4. Stop            ⛔ ");
                    int c = sc.nextInt();
                    audioPlayer.gotoChoice(c, path);
                    if (c == 4)
                    break;
                }

            }
            catch (Exception ex)
            {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
        private void gotoChoice(int c, String path) throws IOException, LineUnavailableException, UnsupportedAudioFileException
        {
            switch (c)
            {
                case 1:
                    pause();
                    break;
                case 2:
                    resumeAudio(path);
                    break;
                case 3:
                    restart(path);
                    break;
                case 4:
                    stop();
                    break;
            }
        }
        public void play()
        {
            //start the clip
            clip.start();

            status = "play";
        }

        public void pause() {
            if (status.equals("paused")) {
                System.out.println("audio is already paused");
                return;
            }
            this.currentFrame = this.clip.getMicrosecondPosition();
            clip.stop();
            status = "paused";
        }

        // Method to resume the audio
        public void resumeAudio(String path) throws UnsupportedAudioFileException,
                IOException, LineUnavailableException
        {
            if (status.equals("play"))
            {
                System.out.println("Audio is already " +
                        "being played");
                return;
            }
            clip.close();
            resetAudioStream(path);
            clip.setMicrosecondPosition(currentFrame);
            this.play();
        }

        // Method to restart the audio
        public void restart(String path) throws IOException, LineUnavailableException,
                UnsupportedAudioFileException
        {
            clip.stop();
            clip.close();
            resetAudioStream(path);
            currentFrame = 0L;
            clip.setMicrosecondPosition(0);
            this.play();
        }

        // Method to stop the audio
        public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException
        {
            currentFrame = 0L;
            clip.stop();
            clip.close();
        }

        // Method to reset audio stream
        public void resetAudioStream(String path) throws UnsupportedAudioFileException, IOException,
                LineUnavailableException
        {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File(path).getAbsoluteFile());
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

    }
