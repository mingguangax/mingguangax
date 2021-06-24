package cn.wolfcode.service;

import cn.wolfcode.domain.Appointment;
import cn.wolfcode.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAppointmentService {
    void insert(Appointment appointment);

    void deleteById(Long id);

    void update(Appointment appointment);

    Appointment selectById(Long id);

    List<Appointment> selectAll();

    PageInfo<Appointment> selectByList(QueryObject qo);

    void save(Appointment appointment);

    void updateStatus(Appointment appointment);
}
