package com.must.ponnex.dsp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by EmmanuelFrancis on 10/20/2015.
 */

public class MainActivity extends AppCompatActivity {

    private Button mOneButton;
    private Button mTwoButton;
    private Button mThreeButton;
    private Button mFourButton;
    private Button mFiveButton;
    private Button mSixButton;
    private Button mSevenButton;
    private Button mEightButton;
    private Button mNineButton;
    private Button mZeroButton;
    private Button mPoundButton;
    private Button mStarButton;

    private static final float values[] = {0.1f, 0.2f, 0.5f, 1.0f, 2.0f, 5.0f, 10.0f, 20.0f, 50.0f, 100.0f, 200.0f, 500.0f};

    private static final int counts[] = {256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288};

    protected static final int SIZE = 20;
    protected static final int DEFAULT_TIMEBASE = 5;
    protected static final float SMALL_SCALE = 200;
    private int DTMF_LENGTH = 300;
    private boolean isLongClick = false;

    protected int timebase;

    private Scope scope;
    private XScale xscale;

    private Audio audio;

    ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_DTMF, ToneGenerator.MAX_VOLUME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        clickListeners();
        initVar();
    }

    public void initVar(){
        audio = new Audio();
        timebase = DEFAULT_TIMEBASE;
        if (scope != null) {
            scope.main = this;
            scope.audio = audio;
        }

        if (scope != null && xscale != null) {
            scope.scale = values[timebase];
            xscale.scale = scope.scale;
            xscale.step = 1000 * xscale.scale;
        }
    }

    public void initUI() {
        scope = (Scope)findViewById(R.id.scope);
        xscale = (XScale)findViewById(R.id.xscale);
        mOneButton = (Button) findViewById(R.id.one);
        mTwoButton = (Button) findViewById(R.id.two);
        mThreeButton = (Button) findViewById(R.id.three);
        mFourButton = (Button) findViewById(R.id.four);
        mFiveButton = (Button) findViewById(R.id.five);
        mSixButton = (Button) findViewById(R.id.six);
        mSevenButton = (Button) findViewById(R.id.seven);
        mEightButton = (Button) findViewById(R.id.eight);
        mNineButton = (Button) findViewById(R.id.nine);
        mZeroButton = (Button) findViewById(R.id.zero);
        mStarButton = (Button) findViewById(R.id.star);
        mPoundButton = (Button) findViewById(R.id.pound);
    }

    public void clickListeners(){
        mOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_1, DTMF_LENGTH); //1209Hz, 697Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mOneButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_1); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_2, DTMF_LENGTH); //1336Hz, 697Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mTwoButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_2); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_3, DTMF_LENGTH); //1477Hz, 697Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mThreeButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_3); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_4, DTMF_LENGTH); //1209Hz, 770Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mFourButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_4); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mFiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_5, DTMF_LENGTH); //1336Hz, 770Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mFiveButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_5); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mSixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_6, DTMF_LENGTH); //1477Hz, 770Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mSixButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_6); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mSevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_7, DTMF_LENGTH); //1209Hz, 852Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mSevenButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_7); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mEightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_8, DTMF_LENGTH); //1336Hz, 852Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mEightButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_8); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mNineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_9, DTMF_LENGTH); //1477Hz, 852Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mNineButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_9); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mZeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_0, DTMF_LENGTH); //1336Hz, 941Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mZeroButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_0); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_S, DTMF_LENGTH); //1209Hz, 941Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mStarButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_S); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
        mPoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLongClick) {
                    toneGenerator.startTone(ToneGenerator.TONE_DTMF_D, DTMF_LENGTH); //1477Hz, 941Hz
                } else {
                    toneGenerator.stopTone();
                    isLongClick = false;
                }
            }
        });
        mPoundButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toneGenerator.startTone(ToneGenerator.TONE_DTMF_D); //1209Hz, 697Hz
                isLongClick = true;
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Start the audio thread

        audio.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        // Stop audio thread

        audio.stop();
    }

    void showAlert(int appName, int errorBuffer)
    {
        // Create an alert dialog builder

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        // Set the title, message and button

        builder.setTitle(appName);
        builder.setMessage(errorBuffer);
        builder.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which)
                    {
                        // Dismiss dialog

                        dialog.dismiss();
                    }
                });
        // Create the dialog

        AlertDialog dialog = builder.create();

        // Show it

        dialog.show();
    }

    protected class Audio implements Runnable
    {
        protected int input;
        protected int sample;

        // Data

        protected Thread thread;
        protected short data[];
        protected long length;

        // Private data

        private static final int SAMPLES = 524288;
        private static final int FRAMES = 4096;

        private static final int INIT  = 0;
        private static final int FIRST = 1;
        private static final int NEXT  = 2;
        private static final int LAST  = 3;

        private AudioRecord audioRecord;
        private short buffer[];

        // Constructor

        protected Audio()
        {
            buffer = new short[FRAMES];
            data = new short[SAMPLES];
        }

        // Start audio

        protected void start()
        {
            // Start the thread

            thread = new Thread(this, "Audio");
            thread.start();
        }

        // Run

        @Override
        public void run()
        {
            processAudio();
        }

        // Stop

        protected void stop()
        {
            Thread t = thread;
            thread = null;

            // Wait for the thread to exit

            while (t != null && t.isAlive())
                Thread.yield();
        }

        // Process Audio

        protected void processAudio()
        {
            // Assume the output sample will work on the input as
            // there isn't an AudioRecord.getNativeInputSampleRate()

            sample =
                    AudioTrack.getNativeOutputSampleRate(AudioManager.STREAM_MUSIC);

            // Get buffer size

            int size =
                    AudioRecord.getMinBufferSize(sample,
                            AudioFormat.CHANNEL_IN_MONO,
                            AudioFormat.ENCODING_PCM_16BIT);
            // Give up if it doesn't work

            if (size == AudioRecord.ERROR_BAD_VALUE ||
                    size == AudioRecord.ERROR ||
                    size <= 0)
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        showAlert(R.string.app_name,
                                R.string.error_buffer);
                    }
                });

                thread = null;
                return;
            }

            // Create the AudioRecord object

            audioRecord =
                    new AudioRecord(input, sample,
                            AudioFormat.CHANNEL_IN_MONO,
                            AudioFormat.ENCODING_PCM_16BIT,
                            size);

            // Check audiorecord

            if (audioRecord == null)
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        showAlert(R.string.app_name,
                                R.string.error_init);
                    }
                });

                thread = null;
                return;
            }

            // Check state

            int state = audioRecord.getState();

            if (state != AudioRecord.STATE_INITIALIZED)
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        showAlert(R.string.app_name,
                                R.string.error_init);
                    }
                });

                audioRecord.release();
                thread = null;
                return;
            }

            // Start recording

            audioRecord.startRecording();

            int index = 0;
            int count = 0;

            state = INIT;
            short last = 0;

            // Continue until the thread is stopped

            while (thread != null)
            {
                // Read a buffer of data

                size = audioRecord.read(buffer, 0, FRAMES);

                // Stop the thread if no data

                if (size == 0)
                {
                    thread = null;
                    break;
                }

                // State machine for sync and copying data to display buffer

                switch (state)
                {
                    // INIT: waiting for sync

                    case INIT:

                        index = 0;

                        // Initialise sync

                        int dx = 0;

                        for (int i = 0; i < size; i++)
                        {
                            dx = buffer[i] - last;

                            if (dx > 0 && last < 0 && buffer[i] > 0)
                            {
                                index = i;
                                state++;
                                break;
                            }

                            last = buffer[i];
                        }

                        // No sync, try next time

                        if (state == INIT)
                            break;

                        // FIRST: First chunk of data

                    case FIRST:

                        // Update count

                        count = counts[timebase];
                        length = count;

                        // Copy data

                        System.arraycopy(buffer, index, data, 0, size - index);
                        index = size - index;

                        // If done, wait for sync again

                        if (index >= count)
                            state = INIT;

                            // Else get some more data next time

                        else
                            state++;
                        break;

                    // NEXT: Subsequent chunks of data

                    case NEXT:

                        // Copy data

                        System.arraycopy(buffer, 0, data, index, size);
                        index += size;

                        // Done, wait for sync again

                        if (index >= count)
                            state = INIT;

                            // Else if last but one chunk, get last chunk next time

                        else if (index + size >= count)
                            state++;
                        break;

                    // LAST: Last chunk of data

                    case LAST:

                        // Copy data

                        System.arraycopy(buffer, 0, data, index, count - index);

                        // Wait for sync next time

                        state = INIT;
                        break;
                }

                // Update display

                scope.postInvalidate();
            }

            // Stop and release the audio recorder

            if (audioRecord != null)
            {
                audioRecord.stop();
                audioRecord.release();
            }
        }
    }
}
