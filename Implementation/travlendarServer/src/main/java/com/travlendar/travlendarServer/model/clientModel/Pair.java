package com.travlendar.travlendarServer.model.clientModel;



public class Pair {
    private long id;
    private boolean isPublic;

    public Pair(long id, boolean isPublic) {
        this.id = id;
        this.isPublic = isPublic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
