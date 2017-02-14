/**
 * 
 */
package com.thravvel.core.data.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.thravvel.core.utils.ThravvelCoreConstants;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class User extends BaseClass {

	@Column(nullable = true)
	private String lastName;

	@Column(nullable = true)
	private String firstName;

	private String birthDate;

	@Column(nullable = true)
	private String birthPlace;

	private char gender;

	@Column(nullable = true)
	private String countryOfOrigin;

	@Column(nullable = true)
	private String regionOfOrigin;

	@Column(nullable = true)
	private String livingCountry;

	@Column(nullable = true)
	private String livingRegion;

	@Column(nullable = true)
	private String livingCity;

	private String emailAdress;

	@Column(nullable = true)
	private String adress;

	@Column(nullable = true)
	private String poBox;

	private String phoneNumber;

	@OneToOne
	@JoinColumn(name = "paymentInfoId", unique = true, nullable = true)
	private PaymentInfo paymentInfo;

	private String userName;

	private String password;

	private Date creationDate;

	@OneToMany
	@Column(nullable = true)
	private List<Picture> pictures;

	private Integer marks;

	private String role;

	@OneToOne
	@JoinColumn(name = "positionId", unique = true, nullable = false)
	private Position position;

	public User(String lastName, String firstName, String birthDate, String birthPlace, char gender,
			String countryOfOrigin, String regionOfOrigin, String livingCountry, String livingRegion, String livingCity,
			String emailAdress, String adress, String poBox, String phoneNumber, PaymentInfo paymentInfo,
			String userName, String password, Picture picture, Position position, Integer marks, String role) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.gender = gender;
		this.countryOfOrigin = countryOfOrigin;
		this.regionOfOrigin = regionOfOrigin;
		this.livingCountry = livingCountry;
		this.livingRegion = livingRegion;
		this.livingCity = livingCity;
		this.emailAdress = emailAdress;
		this.adress = adress;
		this.poBox = poBox;
		this.phoneNumber = phoneNumber;
		this.paymentInfo = paymentInfo;
		this.userName = userName;
		this.password = password;
		this.creationDate = new Date();
		picture.setCurrent(Boolean.TRUE);
		this.pictures = new ArrayList<Picture>();
		pictures.add(picture);
		this.marks = marks == null ? ThravvelCoreConstants.INITIAL_USER_MARKS : marks;
		this.role = role == null ? ThravvelCoreConstants.USER_ROLE : role;
		this.position = position;
	}

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * @param birthPlace
	 *            the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * @return the countryOfOrigin
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	/**
	 * @param countryOfOrigin
	 *            the countryOfOrigin to set
	 */
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	/**
	 * @return the regionOfOrigin
	 */
	public String getRegionOfOrigin() {
		return regionOfOrigin;
	}

	/**
	 * @param regionOfOrigin
	 *            the regionOfOrigin to set
	 */
	public void setRegionOfOrigin(String regionOfOrigin) {
		this.regionOfOrigin = regionOfOrigin;
	}

	/**
	 * @return the livingCountry
	 */
	public String getLivingCountry() {
		return livingCountry;
	}

	/**
	 * @param livingCountry
	 *            the livingCountry to set
	 */
	public void setLivingCountry(String livingCountry) {
		this.livingCountry = livingCountry;
	}

	/**
	 * @return the livingRegion
	 */
	public String getLivingRegion() {
		return livingRegion;
	}

	/**
	 * @param livingRegion
	 *            the livingRegion to set
	 */
	public void setLivingRegion(String livingRegion) {
		this.livingRegion = livingRegion;
	}

	/**
	 * @return the livingCity
	 */
	public String getLivingCity() {
		return livingCity;
	}

	/**
	 * @param livingCity
	 *            the livingCity to set
	 */
	public void setLivingCity(String livingCity) {
		this.livingCity = livingCity;
	}

	/**
	 * @return the emailAdress
	 */
	public String getEmailAdress() {
		return emailAdress;
	}

	/**
	 * @param emailAdress
	 *            the emailAdress to set
	 */
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress
	 *            the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * @return the poBox
	 */
	public String getPoBox() {
		return poBox;
	}

	/**
	 * @param poBox
	 *            the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the paymentInfo
	 */
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	/**
	 * @param paymentInfo
	 *            the paymentInfo to set
	 */
	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the pictures
	 */
	public List<Picture> getPictures() {
		return pictures;
	}

	/**
	 * @param pictures
	 *            the pictures to set
	 */
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	/**
	 * @return the marks
	 */
	public int getMarks() {
		return marks;
	}

	/**
	 * @param marks
	 *            the marks to set
	 */
	public void setMarks(int marks) {
		this.marks = marks;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

}
