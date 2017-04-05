/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thravvel.core.service.impl;

import com.thravvel.core.dao.contract.ICommentDao;
import com.thravvel.core.data.entities.Comment;
import com.thravvel.core.service.CommonService;
import com.thravvel.core.service.contract.ICommentService;
import com.thravvel.core.utils.Exceptions.ThravvelCoreException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fd
 */
@Service
@Transactional
public class CommentServiceImpl extends CommonService implements ICommentService{

    
    private CommentServiceImpl() {
    }
    
    private static Logger logger = Logger.getLogger(CommentServiceImpl.class);
    
    @Autowired
    ICommentDao commentDao;
    
    public Comment createOrUpdateEntity(Comment entity) throws ThravvelCoreException {
       try{
            return commentDao.save(entity);
          } catch (Exception e) {
             ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCORECOMMENTSERVICEERROR-002", new Object[] { entity.getContent() });
             logger.error(coreException.getMessage(), e);
             throw coreException;
          }
    }

    public Comment getEntityById(Long entityId) throws ThravvelCoreException {
       try{   
         return commentDao.findOne(entityId);
        } catch (Exception e) {
	    ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCORECOMMENTSERVICEERROR-003");
	    logger.error(coreException.getMessage(), e);
	    throw coreException;
       }   
    }

    public void deleteEntity(Comment entity) throws ThravvelCoreException {
          try{
             commentDao.delete(entity);
            } catch (Exception e) {
	        ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCORECOMMENTSERVICEERROR-004", new Object[] { entity.getContent() });
	        logger.error(coreException.getMessage(), e);
                throw coreException;
	    }
    }

    public void deleteById(Long entityId) throws ThravvelCoreException {
        try {
			Comment comment = getEntityById(entityId);
			commentDao.delete(comment);
		} catch (ThravvelCoreException tce) {
			throw tce;
		} catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCORECOMMENTSERVICEERROR-005");
			logger.error(coreException.getMessage(), e);
			throw coreException;
		}
    }

    public Page<Comment> getAllEntities(int page, int size) throws ThravvelCoreException {
          try{   
              return commentDao.findAll(new PageRequest(page, size));
            } catch (Exception e) {
			ThravvelCoreException coreException = new ThravvelCoreException(errorMessagesFilePath,
					"THRAVVELCORECOMMENTSERVICEERROR-007");
			logger.error(coreException.getMessage(), e);
			throw coreException;

		}
    }

    public Page<Comment> findEntities(String keyWord, int page, int size) throws ThravvelCoreException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
