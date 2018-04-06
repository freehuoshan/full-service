package com.example.cityapi.models;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SHORT_TITLE")
    private String short_title;

    @Column(name = "AGENCY_NAME")
    private String agency_name;

    @Column(name = "REQUEST_ID")
    private String request_id;

    @Column(name = "SECTION_NAME")
    private String section_name;

    public City(String short_title, String agency_name, String request_id, String section_name) {
        this.short_title = short_title;
        this.agency_name = agency_name;
        this.request_id = request_id;
        this.section_name = section_name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShort_title() {
		return short_title;
	}

	public void setShort_title(String short_title) {
		this.short_title = short_title;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
    
    
}