package botplayingstrategy;

import models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        //have if else to implement diffrent difficulty level
        return new EasyBotPlayingStrategy();
    }
}
