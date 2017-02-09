/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thravvel.core.DaoImplement;

import com.douwe.generic.dao.impl.GenericDao;
import com.thravvel.core.DaoInterface.IMessageDao;
import com.thravvel.core.entities.Message;
import java.io.Serializable;

/**
 *
 * @author fd
 */
public class MessageDaoImpl extends GenericDao<Message, Long> implements IMessageDao{
    
}
