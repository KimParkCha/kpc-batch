package com.ssafy.kpcbatch;

import com.ssafy.kpcbatch.config.DataJobConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private DataJobConfiguration dataJobConfiguration;


    /*
1: 초(0~59)
2: 분(0~59)
3: 시간(0~23)
4: 일(1~31)
5: 월(1~12)
6: 요일(0~7) 0과 7은 일요일, 1부터 월요일 6이 토요일

 */
    @Scheduled(cron = "0 0 2 * * *")
    public void runJob() {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);
        try {
            jobLauncher.run(dataJobConfiguration.dataJob(), jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException | org.springframework.batch.core.repository.JobRestartException e) {

            log.error(e.getMessage());
        }
    }
}
