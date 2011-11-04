/* 
 * =============================================================
 * Copyright (C) 2007-2011 Edgenius (http://www.edgenius.com)
 * =============================================================
 * License Information: http://www.edgenius.com/licensing/edgenius/2.0/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2.0
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * http://www.gnu.org/licenses/gpl.txt
 *  
 * ****************************************************************
 */
package com.edgenius.wiki.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edgenius.wiki.service.CommentService;

/**
 * @author Dapeng.Ni
 */
public class PageCommentNotifyJob extends AuthenticatedQuartzJobBean {
	private static final Logger log = LoggerFactory.getLogger(PageCommentNotifyJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try{
			//not good, directly use DAO...
			CommentService commentService = (CommentService) applicationContext.getBean(CommentService.SERVICE_NAME);
			proxyLoginAsSystemAdmin();
			
			commentService.sendDailyCommentNotify();
			log.info("Job send daily page comments notifiy complete successfully.");
		}finally{
			logout();
		}

	}


}
