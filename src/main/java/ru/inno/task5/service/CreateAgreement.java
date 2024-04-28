package ru.inno.task5.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.inno.task5.Interface.CreateRecordsable;
import ru.inno.task5.productExample.model.InstanceArrangement;
import ru.inno.task5.productExample.model.ProdExample;
import ru.inno.task5.repository.AgreementRepo;
import ru.inno.task5.entity.Agreement;
import ru.inno.task5.entity.Tpp_product;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Qualifier("Agreement") //  Если понадобится несколько реализаций одного интерфейса
public class CreateAgreement implements CreateRecordsable {
    @Autowired
    AgreementRepo agreementRepo;

    Tpp_product tpp_product = new Tpp_product();
    @Override
    public <T, K>  T  create_rec_table(K model) {
        InstanceArrangement agr = (InstanceArrangement) model;
        Agreement agreement = new Agreement();

        agreement.setProductId(Math.toIntExact(tpp_product.getId()));
        agreement.setGeneral_agreement_id(agr.getGeneralAgreementId());
        agreement.setSupplementary_agreement_id(agr.getSupplementaryAgreementId());
        agreement.setArrangement_type(agr.getArrangementType());
        if (!(agr.getShedulerJobId() == null))
            agreement.setSheduler_job_id(Long.valueOf(agr.getShedulerJobId()));
        agreement.setNumber(agr.getNumber());
        agreement.setOpening_date(agr.getOpeningDate().atStartOfDay());
        if(!(agr.getClosingDate() == null))
            agreement.setClosing_date(agr.getClosingDate().atStartOfDay());
        if(!(agr.getCancelDate() == null))
            agreement.setCancel_date(agr.getCancelDate().atStartOfDay());
        if(!(agr.getValidityDuration() == null))
            agreement.setValidity_duration(Long.valueOf(agr.getValidityDuration()));
        agreement.setCancellation_reason(agr.getCancellationReason());
        agreement.setStatus(agr.getStatus());
        if(!(agr.getInterestCalculationDate() == null))
            agreement.setInterest_calculation_date(agr.getInterestCalculationDate().atStartOfDay());
        agreement.setInterest_rate(agr.getInterestRate());
        agreement.setCoefficient(agr.getCoefficient());
        agreement.setCoefficient_action(agr.getCoefficientAction());
        agreement.setMinimum_interest_rate(agr.getMinimumInterestRate());
        if(!(agr.getMinimumInterestRateCoefficient() == null))
            agreement.setMinimum_interest_rate_coefficient(Double.valueOf(agr.getMinimumInterestRateCoefficient()));
        agreement.setMinimum_interest_rate_coefficient_action(agr.getMinimumInterestRateCoefficientAction());
        agreement.setMaximal_interest_rate(agr.getMaximalInterestRate());
        agreement.setMaximal_interest_rate_coefficient(agr.getMaximalInterestRateCoefficient());
        agreement.setMaximal_interest_rate_coefficient_action(agr.getMaximalInterestRateCoefficientAction());
        Agreement agreementAdd =  agreementRepo.save(agreement);

        System.out.println("Добавлена запись в Agreement");
        return (T) agreementAdd;
    }

    @Override
    public <T, K> List<T> create_recs_table(K model){
        ProdExample prodExample = (ProdExample) model;
        List<Agreement> agreementLst = new ArrayList<>();
        List<InstanceArrangement> instAgr =  prodExample.getInstanceArrangement();
        for (InstanceArrangement  agr : instAgr) {
            Agreement agreement = create_rec_table(agr);
            agreementLst.add(agreement);
        }
        return  (List<T> ) agreementLst;
    }

    // Вспомогательный функционал
    public List<Agreement> create_recs_table_agreement(ProdExample prodExample, Tpp_product tpp_product) {
        this.tpp_product = tpp_product;
        return  create_recs_table(prodExample);
    }

    public List<Agreement> findAllAgreement(Tpp_product tpp_product){
        return agreementRepo.findByProductId(Math.toIntExact(tpp_product.getId()));
    }
}