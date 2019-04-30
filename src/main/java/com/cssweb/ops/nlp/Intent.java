package com.cssweb.ops.nlp;

public class Intent {
    private int rc;
    private String sid;
    private String text;
    private String uuid;


    private Answer answer;

    private String category;



    private IntentData data;
    private String intentType;

    private Semantic[] semantic;
    private int semanticType;

    private String service;
    private boolean shouldEndSession;

    private State state;

    private String vendor;
    private String version;

    private MoreResults[] moreResults;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public IntentData getData() {
        return data;
    }

    public void setData(IntentData data) {
        this.data = data;
    }

    public String getIntentType() {
        return intentType;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public Semantic[] getSemantic() {
        return semantic;
    }

    public void setSemantic(Semantic[] semantic) {
        this.semantic = semantic;
    }

    public int getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(int semanticType) {
        this.semanticType = semanticType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isShouldEndSession() {
        return shouldEndSession;
    }

    public void setShouldEndSession(boolean shouldEndSession) {
        this.shouldEndSession = shouldEndSession;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public MoreResults[] getMoreResults() {
        return moreResults;
    }

    public void setMoreResults(MoreResults[] moreResults) {
        this.moreResults = moreResults;
    }
}
