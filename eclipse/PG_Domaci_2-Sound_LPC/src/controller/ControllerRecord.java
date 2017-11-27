package controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.SoundRecorder;
import view.View;

public class ControllerRecord implements EventHandler<ActionEvent>
{

	public View view;
	public static int NUMBER = 0;
	static final long RECORD_TIME = 3000;
	
	public ControllerRecord(View view) {
		super();
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		
		String string = "Record in ";
		String filePath = "/sounds/sound_" + NUMBER + ".wav";
		
		Task<Void> task = new Task<Void>()
		{
			public Void call()
			{
				long timeStart = System.currentTimeMillis();
				long timeCurrent = System.currentTimeMillis();
				long timeDelta = timeCurrent - timeStart;
				
				while(timeDelta <= 2000)
				{
					int timeSeconds = (int) (timeDelta / 1000);
					updateMessage(string + (2 - timeSeconds) + " ...");
					
					//pointing

					timeCurrent = System.currentTimeMillis();
					timeDelta = timeCurrent - timeStart;
				}
				
				timeStart = System.currentTimeMillis();
				timeCurrent = System.currentTimeMillis();
				timeDelta = timeCurrent - timeStart;
				
				final SoundRecorder recorder = new SoundRecorder(filePath);
				recorder.start();
				
				while(timeDelta <= 3000)
				{
					int timeSeconds = (int) (timeDelta / 1000);
					updateMessage("Recording(" + (3 - timeSeconds) + ")...");

					/*
					//recording
					final SoundRecorder recorder = new SoundRecorder(filePath);
					
			        Thread stopper = new Thread(new Runnable() {
			            public void run() {
			                try {
			                    Thread.sleep(RECORD_TIME);
			                } catch (InterruptedException ex) {
			                    ex.printStackTrace();
			                }
			                recorder.finish();
			            }
			        });
			        stopper.start();
			        recorder.start();	
			        */
			        
			        
			        timeCurrent = System.currentTimeMillis();
					timeDelta = timeCurrent - timeStart;
				}
				recorder.finish();
				NUMBER++;
				updateMessage("");
				return null;		
			}	
		};
		task.messageProperty().addListener((obs, oldMessage, newMessage) -> view.getLblRecord().setText(newMessage));
        new Thread(task).start();

	}

}
