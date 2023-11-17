package com.ssafy.kpcbatch.writer;

import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailDto;
import com.ssafy.kpcbatch.dto.complexDetail.ComplexDetailsDto;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ComplexDetailWriter implements ItemWriter<ComplexDetailsDto> {
    private final DataSource dataSource;
    private final JdbcBatchItemWriter jdbcBatchItemWriter;

    public ComplexDetailWriter(DataSource dataSource, JdbcBatchItemWriter jdbcBatchItemWriter) {
        this.dataSource = dataSource;
        this.jdbcBatchItemWriter = jdbcBatchItemWriter;
    }


    @Override
    public void write(List<? extends ComplexDetailsDto> items) throws Exception {
        List<ComplexDetailDto> complexDetails = new ArrayList<>();
        complexDetails.add(items.get(0).getComplexDetail());

        String sql = "INSERT INTO complex_detail" +
                "(complex_no, complex_name, cortar_no, real_estate_type_code, real_estate_type_name," +
                "detail_address, road_address, latitude, longitude, total_household_count," +
                "total_lease_household_count, permanent_lease_household_count, nation_lease_household_count," +
                "civil_lease_household_count, public_lease_household_count, long_term_lease_household_count," +
                "etc_lease_household_count, high_floor, low_floor, use_approve_ymd, total_dong_count," +
                "max_supply_area, min_supply_area, deal_count, rent_count, lease_count, short_term_rent_count," +
                "is_bookmarked, batl_ratio, btl_ratio, parking_possible_count, parking_count_by_household," +
                "construction_company_name, heat_method_type_code, heat_fuel_type_code, pyoeng_names," +
                "management_office_tel_no, building_register, address, road_address_prefix, road_zip_code) values" +
                "(:complexNo, :complexName, :cortarNo, :realEstateTypeCode, :realEstateTypeName," +
                ":detailAddress, :roadAddress, :latitude, :longitude, :totalHouseholdCount," +
                ":totalLeaseHouseholdCount, :permanentLeaseHouseholdCount, :nationLeaseHouseholdCount," +
                ":civilLeaseHouseholdCount, :publicLeaseHouseholdCount, :longTermLeaseHouseholdCount," +
                ":etcLeaseHouseholdCount, :highFloor, :lowFloor, :useApproveYmd, :totalDongCount," +
                ":maxSupplyArea, :minSupplyArea, :dealCount, :rentCount, :leaseCount, :shortTermRentCount," +
                ":isBookmarked, :batlRatio, :btlRatio, :parkingPossibleCount, :parkingCountByHousehold," +
                ":constructionCompanyName, :heatMethodTypeCode, :heatFuelTypeCode, :pyoengNames," +
                ":managementOfficeTelNo, :buildingRegister, :address, :roadAddressPrefix, :roadZipCode)";

        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setJdbcTemplate(new NamedParameterJdbcTemplate(dataSource));
        jdbcBatchItemWriter.setSql(sql);
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider());
        jdbcBatchItemWriter.afterPropertiesSet();
        jdbcBatchItemWriter.write(complexDetails);

    }
}
