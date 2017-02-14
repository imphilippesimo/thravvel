/**
 * 
 */
package com.thravvel.core.data.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GeneralComment extends BaseClass {

	@Column
	@ElementCollection(targetClass = String.class)
	private List<String> reactions;

	@OneToOne
	@JoinColumn(name = "commentatorUserId", unique = true, nullable = false, updatable = false)
	private User commentator;
	@OneToOne
	@JoinColumn(name = "stationId", unique = true, nullable = false, updatable = false)
	private Station commented;
	@Lob
	private String content;
	private Integer abusiveRate;

	/**
	 * @return the reactions
	 */
	public List<String> getReactions() {
		return reactions;
	}

	/**
	 * @param reactions
	 *            the reactions to set
	 */
	public void setReactions(List<String> reactions) {
		this.reactions = reactions;
	}

	/**
	 * @return the commentator
	 */
	public User getCommentator() {
		return commentator;
	}

	/**
	 * @param commentator
	 *            the commentator to set
	 */
	public void setCommentator(User commentator) {
		this.commentator = commentator;
	}

	/**
	 * @return the commented
	 */
	public Station getCommented() {
		return commented;
	}

	/**
	 * @param commented
	 *            the commented to set
	 */
	public void setCommented(Station commented) {
		this.commented = commented;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the abusiveRate
	 */
	public Integer getAbusiveRate() {
		return abusiveRate;
	}

	/**
	 * @param abusiveRate
	 *            the abusiveRate to set
	 */
	public void setAbusiveRate(Integer abusiveRate) {
		this.abusiveRate = abusiveRate;
	}

	/**
	 * @param reactions
	 * @param commentator
	 * @param commented
	 * @param content
	 * @param abusiveRate
	 */
	public GeneralComment(List<String> reactions, User commentator, Station commented, String content,
			Integer abusiveRate) {
		super();
		this.reactions = reactions;
		this.commentator = commentator;
		this.commented = commented;
		this.content = content;
		this.abusiveRate = abusiveRate;
	}

	/**
	 * 
	 */
	public GeneralComment() {
		// TODO Auto-generated constructor stub
	}

}
