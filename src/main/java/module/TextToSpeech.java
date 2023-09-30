package module;

import javax.speech.*;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TextToSpeech {

    public static void main(String[] args) throws EngineException, AudioException, EngineStateError, InterruptedException {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText("Hello", null);
            synthesizer.waitEngineState(synthesizer.QUEUE_EMPTY);
            synthesizer.deallocate();
        } catch (Exception e) {

        }

    }
}
