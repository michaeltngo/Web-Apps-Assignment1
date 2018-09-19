package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;

public class AlbumDAO {
    private JdbcTemplate jdbcTemplate;

    public AlbumDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Album createAlbum(Album album){
        //TODO: Implement this CRUD function
        String SQL = "INSERT INTO albums (id, title) values (?, ?)";
        jdbcTemplate.update (SQL, album.getId(), album.getTitle());

        System.out.println("Album created, id= " + album.getId() + " title= " +album.getTitle());
        return album;
    }
    
   public Album getAlbum(int id){
        Album album = new Album(id, "");
        //TODO: Implement this CRUD function
        album = this.jdbcTemplate.queryForObject(
                "SELECT * FROM album WHERE id = ?",
                new Object[]{album.getId()},
                (rs, rowNum) -> new Album(rs.getInt("id"), rs.getString("title"))
        );
        System.out.println("Track from getTrack: " + album);
        return album;
    }

    public Collection<Album> getAllAlbums(){
        Collection<Album> albums = new ArrayList<Album>();
        this.jdbcTemplate.query(
                "SELECT * FROM albums", new Object[] { },
                (rs, rowNum) -> new Album(rs.getInt("id"), rs.getString("title"))
        ).forEach(album -> albums.add(album));

        return albums;
    }

    public Album updateAlbum(Album album){
        //TODO: Implement this CRUD function
        String SQL = "update albums set id=?, title=? where id=?";
        jdbcTemplate.update(SQL, album.getId(), album.getTitle(), album.getId());
        System.out.println("Updated album with id = "
                + album.getId() + ", title = " + album.getTitle());

        return album;
    }

    public boolean deleteAlbum(Album album){
        boolean success = false;
        //TODO: Implement this CRUD function
        String SQL = "delete from albums where id = ?";
        jdbcTemplate.update(SQL, album.getId());
        System.out.println("Deleted Album with Title = "
                + album.getTitle() + ", AlbumId = "
                + album.getId());

        return success;
    }



}
