# scheduling
- [Scheduling Tasks](https://spring.io/guides/gs/scheduling-tasks/)

---

# Batch
- [Spring Batch Reference](https://docs.spring.io/spring-batch/4.0.x/reference/html/index.html)
- [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/#_see_also)
- [Spring Batch 학습 자료](http://chanwookpark.github.io/spring/springbatch/2016/01/26/spring-batch-doc/)


## console

`chunk`가 2인 경우,

```text
[           main] com.heowc.DemoApplication                : Started DemoApplication in 1.535 seconds (JVM running for 2.366)
[           main] o.s.b.a.b.JobLauncherCommandLineRunner   : Running default command line with: []
[           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=importUserJob]] launched with the following parameters: [{run.id=1}]
[           main] o.s.batch.core.job.SimpleStepHandler     : Executing step: [step1]
[           main] com.heowc.PersonItemProcessor            : Converting (firstName: Jill, lastName: Doe) into (firstName: JILL, lastName: DOE)
[           main] com.heowc.PersonItemProcessor            : Converting (firstName: Joe, lastName: Doe) into (firstName: JOE, lastName: DOE)
[           main] o.s.b.item.database.JdbcBatchItemWriter  : Executing batch with 2 items.
[           main] com.heowc.PersonItemProcessor            : Converting (firstName: Justin, lastName: Doe) into (firstName: JUSTIN, lastName: DOE)
[           main] com.heowc.PersonItemProcessor            : Converting (firstName: Jane, lastName: Doe) into (firstName: JANE, lastName: DOE)
[           main] o.s.b.item.database.JdbcBatchItemWriter  : Executing batch with 2 items.
[           main] com.heowc.PersonItemProcessor            : Converting (firstName: John, lastName: Doe) into (firstName: JOHN, lastName: DOE)
[           main] o.s.b.item.database.JdbcBatchItemWriter  : Executing batch with 1 items.
[           main] c.h.JobCompletionNotificationListener    : !!! JOB FINISHED! Time to verify the results
[           main] c.h.JobCompletionNotificationListener    : Found <firstName: JILL, lastName: DOE> in the database.
[           main] c.h.JobCompletionNotificationListener    : Found <firstName: JOE, lastName: DOE> in the database.
[           main] c.h.JobCompletionNotificationListener    : Found <firstName: JUSTIN, lastName: DOE> in the database.
[           main] c.h.JobCompletionNotificationListener    : Found <firstName: JANE, lastName: DOE> in the database.
[           main] c.h.JobCompletionNotificationListener    : Found <firstName: JOHN, lastName: DOE> in the database.
[           main] o.s.b.c.l.support.SimpleJobLauncher      : Job: [FlowJob: [name=importUserJob]] completed with the following parameters: [{run.id=1}] and the following status: [COMPLETED]
```