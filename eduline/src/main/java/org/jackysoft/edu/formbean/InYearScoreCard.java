package org.jackysoft.edu.formbean;

import java.util.ArrayList;
import java.util.List;

import org.jackysoft.edu.entity.ExamScore;

public class InYearScoreCard {
	protected ExamScore scoreInfo ;
	protected List<InYearScoreDetail> scoreDetails ;
	protected int page;	
    protected long counts;
	
	public InYearScoreCard() {
		this(new ExamScore(),new ArrayList<>(),0,0L);
	}



	public InYearScoreCard(ExamScore info, List<InYearScoreDetail> list,int page,long counts) {
		super();
		this.page = page;
		this.scoreInfo = info;
		this.scoreDetails = list;
		this.counts = counts;
		
	}



	public ExamScore getScoreInfo() {
		return scoreInfo;
	}



	public List<InYearScoreDetail> getScoreDetails() {
		return scoreDetails;
	}



	public int getPage() {
		return page;
	}

	public int getPrev() {
		return page==0?-1:(page-1);
	}


	

	public long getCounts() {
		return counts;
	}



	public int getNext() {
		return page<(counts-1)?(page+1):-1;
	}
	
	
	
	
	
	
	
	
	
	
}
