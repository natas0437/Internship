package com.BootCampexample.kelompok4.backend.service;


import com.BootCampexample.kelompok4.backend.dao.KnowledgeDao;
import com.BootCampexample.kelompok4.backend.dto.KnowledgeDto;
import com.BootCampexample.kelompok4.backend.entity.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.PushBuilder;
import java.util.List;

@Service
public class KnowledgeService {

    @Autowired
    KnowledgeDao dao;

    public Knowledge findId(Integer id)
        throws EmptyResultDataAccessException {
        return dao.findId(id);
    }

    public List<Knowledge> findAll() {return dao.findAll();}

    @Transactional
    public Integer create(KnowledgeDto.Create knowledge) {return dao.create(knowledge);}

    @Transactional
    public void update(KnowledgeDto.Update knowledge) {dao.update(knowledge);}

    @Transactional
    public void delete(Integer id) {dao.delete(id);}

}


