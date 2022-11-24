package myclasses;

public class checkAttackError {
    String command;
    String error;
    coordonate cardAttacker, cardAttacked;

    public checkAttackError(String command, String error, coordonate cardAttacker, coordonate cardAttacked) {
        this.setCommand(command);
        this.setError(error);
        this.setCardAttacker(cardAttacker);
        this.setCardAttacked(cardAttacked);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public coordonate getCardAttacker() {
        return cardAttacker;
    }

    public void setCardAttacker(coordonate ccardAttacker) {
        this.cardAttacker = ccardAttacker;
    }

    public coordonate getCardAttacked() {
        return cardAttacked;
    }

    public void setCardAttacked(coordonate cardAttacked) {
        this.cardAttacked = cardAttacked;
    }
}
