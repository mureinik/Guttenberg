package org.sobitics.guttenberg.commandlists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobotics.guttenberg.commands.*;

import fr.tunaki.stackoverflow.chat.Message;
import fr.tunaki.stackoverflow.chat.Room;
import fr.tunaki.stackoverflow.chat.event.PingMessageEvent;


/**
 * Created by bhargav.h on 28-Oct-16.
 */
public class SoBoticsCommandsList {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoBoticsCommandsList.class);

    public void mention(Room room, PingMessageEvent event, boolean isReply){
    	System.out.println("Someone mentioned me");
        /*if(CheckUtils.checkIfUserIsBlacklisted(event.getUserId()))
            return;*/

        Message message = event.getMessage();
        List<SpecialCommand> commands = new ArrayList<>(Arrays.asList(
            new Alive(message),
            new Say(message)
        ));

        commands.add(new Commands(message,commands));
        
        System.out.println("Looking for the command to execute");

        for(SpecialCommand command: commands){
            if(command.validate()){
                command.execute(room);
            } else {
            	System.out.println("It's not " +command.description());
            }
        }
        
        LOGGER.info(event.getMessage().getContent());
    }
}
