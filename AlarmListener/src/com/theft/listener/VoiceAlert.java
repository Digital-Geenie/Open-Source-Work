package com.theft.listener;

import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class VoiceAlert {
 public static void main(String[] args){
 try
 {
   System.setProperty("freetts.voices",
    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    
   Central.registerEngineCentral
    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
   Synthesizer  synthesizer = 
    Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
   synthesizer.allocate();
   synthesizer.resume();
   synthesizer.speakPlainText("Collision Ahead! Stop Now", null);
   synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
   synthesizer.deallocate();
  }
   catch(Exception e)
   {
     e.printStackTrace();
   }
 }

private Synthesizer synthesizer;

public void init(){
	try{
		   System.setProperty("freetts.voices",
				    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
				    
				   Central.registerEngineCentral
				    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
				   synthesizer = 
				    Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
				   synthesizer.allocate();
				   synthesizer.resume();
	}
	   catch(Exception e)
	   {
	     e.printStackTrace();
	   }
}

public  void playText(String string) {
	// TODO Auto-generated method stub
	try
	 {

			   synthesizer.speakPlainText("Theft Alert", null);
			   synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
//			   synthesizer.deallocate();
			  }
			   catch(Exception e)
			   {
			     e.printStackTrace();
			   }
	
}
 

 
}