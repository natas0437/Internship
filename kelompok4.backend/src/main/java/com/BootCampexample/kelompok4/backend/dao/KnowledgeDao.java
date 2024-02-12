package com.BootCampexample.kelompok4.backend.dao;

import com.BootCampexample.kelompok4.backend.dto.KnowledgeDto;
import com.BootCampexample.kelompok4.backend.entity.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class KnowledgeDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public Knowledge findId(Integer id) {
        String query = "SELECT id, nama, rating, status, App_users_id\n" +
                "FROM public.knowledge where id = :idKnowledge";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("idKnowledge", id);

        return jdbcTemplate.queryForObject(query, map, new RowMapper<Knowledge>() {
            @Override
            public Knowledge mapRow(ResultSet rs, int rowNum) throws SQLException {
                Knowledge knowledge = new Knowledge();
                knowledge.setId(rs.getInt("id"));
                knowledge.setNama(rs.getString("nama"));
                knowledge.setRating(rs.getInt("rating"));
                knowledge.setStatus(rs.getString("status"));
                knowledge.setApp_users_id(rs.getInt("app_users_id"));
                return knowledge;
            }
        });
    }

    public List<Knowledge> findAll() {
        String query = "SELECT id, nama, rating, status, App_users_id\n" +
                "FROM public.knowledge";
        return jdbcTemplate.query(query, new RowMapper<Knowledge>() {

            @Override
            public Knowledge mapRow(ResultSet rs, int rowNum) throws SQLException {
                Knowledge knowledge = new Knowledge();
                knowledge.setId(rs.getInt("id"));
                knowledge.setNama(rs.getString("nama"));
                knowledge.setRating(rs.getInt("rating"));
                knowledge.setStatus(rs.getString("status"));
                knowledge.setApp_users_id(rs.getInt("app_users_id"));
                return knowledge;


            }
        });

    }

    public Integer create(KnowledgeDto.Create knowledge) {
        String query = "INSERT INTO public.knowledge\n" +
                "(nama, rating, status, app_users_id)\n" +
                "VALUES(:nama, :rating, :status, :app_users_id)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", knowledge.getNama());
        map.addValue("rating", knowledge.getRating());
        map.addValue("status", knowledge.getStatus() );
        map.addValue("app_users_id", knowledge.getApp_users_id());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query,map,keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
     }
    public void update(KnowledgeDto.Update knowledge){
        String query = "UPDATE public.knowledge\n" +
                "SET nama=:nama, rating=:rating, status=:status, app_users_id=:app_users_id\n" +
                "WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", knowledge.getNama());
        map.addValue("rating", knowledge.getRating());
        map.addValue("id", knowledge.getId());
        map.addValue("status", knowledge.getStatus());
        map.addValue("app_users_id", knowledge.getApp_users_id() );
        jdbcTemplate.update(query, map);
    }

    public void delete(Integer id) {
        String query = "DELETE FROM public.knowledge\n" +
                "WHERE id=:id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        jdbcTemplate.update(query, map);
    }
}
