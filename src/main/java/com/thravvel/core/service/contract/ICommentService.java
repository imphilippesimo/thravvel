/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thravvel.core.service.contract;

import com.thravvel.core.data.entities.Comment;
import com.thravvel.core.service.IGenericService;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fd
 */
@Transactional
public interface ICommentService extends IGenericService<Comment>{
    
}
