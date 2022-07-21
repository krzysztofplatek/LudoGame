package MySql;

public class Score
{
    private String redNickname;
    private String greenNickname;
    private String yellowNickname;
    private String blueNickname;
    private int redScore;
    private int greenScore;
    private int yellowScore;
    private int blueScore;
    public Score(String redNickname, String greenNickname, String yellowNickname, String blueNickname, int redScore, int greenScore, int yellowScore, int blueScore) {
        this.redNickname = redNickname;
        this.greenNickname = greenNickname;
        this.yellowNickname = yellowNickname;
        this.blueNickname = blueNickname;
        this.redScore = redScore;
        this.greenScore = greenScore;
        this.yellowScore = yellowScore;
        this.blueScore = blueScore;
    }
    public void addScore()
    {
        MySQLAccess mysql= new MySQLAccess();
        try {
            mysql.readDataBase();
            mysql.writeToDataBase(redNickname,redScore,blueNickname,blueScore,yellowNickname,yellowScore,blueNickname,blueScore);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
