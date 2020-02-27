package model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class MatchData implements Parcelable {
    public String homeTeam;
    public String awayTeam;
    public Uri homeUri;
    public Uri awayUri;
    public int scoreHome;
    public int scoreAway;
    public String info;

    public MatchData(String homeTeam, String awayTeam, Uri homeUri, Uri awayUri, int scoreHome, int scoreAway, String info) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.info = info;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Uri getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }

    public int getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(int scoreHome) {
        this.scoreHome = scoreHome;
    }

    public int getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(int scoreAway) {
        this.scoreAway = scoreAway;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.homeTeam);
        dest.writeString(this.awayTeam);
        dest.writeParcelable(this.homeUri, flags);
        dest.writeParcelable(this.awayUri, flags);
        dest.writeInt(this.scoreHome);
        dest.writeInt(this.scoreAway);
        dest.writeString(this.info);
    }

    protected MatchData(Parcel in) {
        this.homeTeam = in.readString();
        this.awayTeam = in.readString();
        this.homeUri = in.readParcelable(Uri.class.getClassLoader());
        this.awayUri = in.readParcelable(Uri.class.getClassLoader());
        this.scoreHome = in.readInt();
        this.scoreAway = in.readInt();
        this.info = in.readString();
    }

    public static final Creator<MatchData> CREATOR = new Creator<MatchData>() {
        @Override
        public MatchData createFromParcel(Parcel source) {
            return new MatchData(source);
        }

        @Override
        public MatchData[] newArray(int size) {
            return new MatchData[size];
        }
    };
}
