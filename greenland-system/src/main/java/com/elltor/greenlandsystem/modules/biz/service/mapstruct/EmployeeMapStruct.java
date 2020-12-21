package com.elltor.greenlandsystem.modules.biz.service.mapstruct;

import com.elltor.greenlandcommon.base.BaseMapStruct;
import com.elltor.greenlandsystem.modules.biz.entity.Employee;
import com.elltor.greenlandsystem.modules.biz.entity.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 *    employee map struct utils
 * </p>
 *
 * @author: LiuQichun
 * @date: 2020/12/19 10:44
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapStruct extends BaseMapStruct<EmployeeDTO,Employee> {
}
