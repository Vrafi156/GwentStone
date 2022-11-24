package myclasses;

public class checkAttackHero {
    String  command;
    String error;
    coordonate cardAttacker;

    public String getCommand() {
        return command;
    }

    public checkAttackHero(String command, String error, coordonate cardAttacker) {
        this.command = command;
        this.error = error;
        this.cardAttacker = cardAttacker;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public coordonate getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(coordonate cardAttacker) {
        this.cardAttacker = cardAttacker;
    }
}
