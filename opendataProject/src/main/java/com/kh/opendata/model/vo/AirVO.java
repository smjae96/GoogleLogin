package com.kh.opendata.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirVO {

	private String stationName;		// 측정소이름
	private String dataTime;		// 측정일시
	private String khaiValue;		// 통합대기환경 수치
	
	private String pm10Value;		// 미세먼지농도
	private String so2Value;		// 아황산가스농도
	private String coValue;			// 일산화탄소농도
	private String no2Value;		// 이산화질소농도
	private String o3Value; 		// 오존농도
	
}
