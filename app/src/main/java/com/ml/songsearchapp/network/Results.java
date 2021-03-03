
package com.ml.songsearchapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ml.songsearchapp.domain.SongMatches;

public class Results {

    @SerializedName("trackmatches")
    @Expose
    private SongMatches songmatches;

    public SongMatches getSongmatches() {
        return songmatches;
    }

    public void setSongmatches(SongMatches songmatches) {
        this.songmatches = songmatches;
    }

}
