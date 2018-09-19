package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;


public class TrackDAO {
    private JdbcTemplate jdbcTemplate;

    public TrackDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }


    public Track createTrack(Track track){
        //TODO: Implement this CRUD function
        String SQL = "INSERT INTO tracks (title, album) values (?, ?)";
        jdbcTemplate.update (SQL, track.getTitle(), track.getAlbumId());
        return track;
    }

    public Track getTrack(int id){
        Track track = new Track(id);
        //TODO: Implement this CRUD function

        track = this.jdbcTemplate.queryForObject(
                "SELECT * FROM tracks WHERE id = ?",
                new Object[]{track.getId()},
                (rs, rowNum) -> new Track(rs.getInt("id"))
        );
        System.out.println("Track from getTrack: " + track);
        return track;
    }

    public Collection<Track> getAllTracks(){

        Collection<Track> tracks = new ArrayList<Track>();
        //TODO: Implement this CRUD function
        this.jdbcTemplate.query(
                "SELECT * FROM tracks", new Object[] { },
                (rs, rowNum) -> new Track(rs.getInt("id"), rs.getString("title"), rs.getInt("album"))
        ).forEach(track -> tracks.add(track));


        System.out.println("Tracks from getAllTracks: " + tracks);
        return tracks;
    }

    public Collection<Track> getTracksByAlbumId(int albumId){
        Collection<Track> tracks = new ArrayList<Track>();

        this.jdbcTemplate.query(
                "SELECT id, title FROM tracks WHERE album = ?", new Object[] { albumId },
                (rs, rowNum) -> new Track(rs.getInt("id"), rs.getString("title"),albumId)
        ).forEach(track -> tracks.add(track) );

        return tracks;
    }
    public Track updateTrack(Track track){
        //TODO: Implement this CRUD function
        String SQL = "update tracks set title=?, album=? where id=?";
        jdbcTemplate.update(SQL, track.getTitle(), track.getAlbumId(), track.getId());
        System.out.println("Updated track with Title = "
                + track.getTitle() + ", albumId = " + track.getAlbumId());
        return track;
    }

    public boolean deleteTrack(Track track){
        boolean success = false;
        //TODO: Implement this CRUD function
        String SQL = "delete from tracks where id = ?";
        jdbcTemplate.update(SQL, track.getId());
        System.out.println("Deleted Album with Title = "
                + track.getTitle() + ", AlbumId = "
                + track.getAlbumId());
        return success;
    }

}
