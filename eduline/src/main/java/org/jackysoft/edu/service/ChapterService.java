package org.jackysoft.edu.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jackysoft.edu.entity.Chapter;
import org.jackysoft.edu.service.base.AbstractMongoService;
import org.springframework.stereotype.Service;


@Service
public class ChapterService extends AbstractMongoService<Chapter> {


    static final Log logger = LogFactory.getLog(ChapterService.class);


}
