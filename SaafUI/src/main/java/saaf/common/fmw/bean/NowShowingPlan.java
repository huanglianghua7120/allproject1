package saaf.common.fmw.bean;


public class NowShowingPlan {
    private String cinemaOuterId;
    private String sessionOutId;

    public NowShowingPlan() {
    }

    public NowShowingPlan(String cinemaOuterId, String sessionOutId) {
        this.cinemaOuterId = cinemaOuterId;
        this.sessionOutId = sessionOutId;
    }

    public void setCinemaOuterId(String cinemaOuterId) {
        this.cinemaOuterId = cinemaOuterId;
    }

    public String getCinemaOuterId() {
        return cinemaOuterId;
    }

    public void setSessionOutId(String sessionOutId) {
        this.sessionOutId = sessionOutId;
    }

    public String getSessionOutId() {
        return sessionOutId;
    }
}
