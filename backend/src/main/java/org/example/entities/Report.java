package org.example.entities;

public class Report {
    public int id;
    public User reporter;
    public User reported;
    public String evidence;
    public int state;
    public Order order;

    public ScreenShot evidencePicture;

    public Report(int id, User reporter, User reported, String evidence, int state, ScreenShot evidencePicture, Order order) {
        this.id = id;
        this.reporter = reporter;
        this.reported = reported;
        this.evidence = evidence;
        this.state = state;
        this.order = order;
        this.evidencePicture = evidencePicture;
    }

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getReported() {
        return reported;
    }

    public void setReported(User reported) {
        this.reported = reported;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ScreenShot getEvidencePicture() {
        return evidencePicture;
    }

    public void setEvidencePicture(ScreenShot evidencePicture) {
        this.evidencePicture = evidencePicture;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reporter=" + reporter +
                ", reported=" + reported +
                ", evidence='" + evidence + '\'' +
                ", state=" + state +
                ", order=" + order +
                ", evidencePicture=" + evidencePicture +
                '}';
    }
}
