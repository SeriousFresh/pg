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
				
				final SoundRecorder recorder = new SoundRecorder(filePath);
				
				while(timeDelta <= 3000)
				{
					int timeSeconds = (int) (timeDelta / 1000);
					updateMessage(string + (3 - timeSeconds) + " ...");
					
					//pointing
					if(timeDelta == 1000)
						recorder.start();

					timeCurrent = System.currentTimeMillis();
					timeDelta = timeCurrent - timeStart;
				}
				
				while(timeDelta <= 6000)
				{
					int timeSeconds = (int) (timeDelta / 1000);
					updateMessage("Recording(" + (6 - timeSeconds) + ")...");

					timeCurrent = System.currentTimeMillis();
					timeDelta = timeCurrent - timeStart;
				}
				updateMessage("");
				recorder.finish();
				NUMBER++;
				return null;		
			}	
		};
		task.messageProperty().addListener((obs, oldMessage, newMessage) -> view.getLblRecord().setText(newMessage));
        new Thread(task).start();

	}

}
