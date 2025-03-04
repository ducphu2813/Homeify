package com.homeify.serviceinfo.serviceinfoapi.Controller;

import com.homeify.serviceinfo.Entities.Transportation;
import com.homeify.serviceinfo.UseCases.TransportationUsecase;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation.SaveTransportDTO;
import com.homeify.serviceinfo.serviceinfoapi.DTO.Transportation.TransportationDTO;
import com.homeify.serviceinfo.serviceinfoapi.Mapper.TransportationDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportation")
public class TransportationController {

    private final TransportationUsecase transportationUsecase;
    private final TransportationDTOMapper transportationDTOMapper;

    public TransportationController(TransportationUsecase transportationUsecase
                                    , TransportationDTOMapper transportationDTOMapper) {
        this.transportationUsecase = transportationUsecase;
        this.transportationDTOMapper = transportationDTOMapper;
    }

    //get all
    @GetMapping("/getAll")
    public List<TransportationDTO> getAll() {

        List<Transportation> transportations = transportationUsecase.getAllTransportation();

        //chuyển từ List<Transportation> sang List<TransportationDTO>
        //dùng mapper
        return transportationDTOMapper.toTransportationDTOs(transportations);

    }

    //thêm
    @PostMapping("/add")
    public TransportationDTO add(@RequestBody SaveTransportDTO transportationDTO) {

        Transportation transportation = transportationDTOMapper.toTransportation(transportationDTO);

        transportation = transportationUsecase.addTransportation(transportation);

        return transportationDTOMapper.toTransportationDTO(transportation);

    }

    //sửa
    @PutMapping("/update/{id}")
    public TransportationDTO update(@RequestBody SaveTransportDTO transportationDTO, @PathVariable String id) {

        Transportation transportation = transportationDTOMapper.toTransportation(transportationDTO);

        transportation = transportationUsecase.updateTransportation(transportation, id);

        return transportationDTOMapper.toTransportationDTO(transportation);

    }

    //xóa
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        transportationUsecase.deleteTransportation(id);

    }
}
