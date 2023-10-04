def microservices = ["adservice", "cartservice", "checkoutservice", "currencyservice", "emailservice", "frontend", 
"loadgenerator", "paymentservice", "productcatalogservice", "recommendationservice", "shippingservice"]

microservices.each { jobName ->
    pipelineJob(jobName) {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url('https://gitlab.com/gitops-freddieentity/application')
                        }
                        branch('*/main')
                    }
                }
                scriptPath(jobName + '/Jenkinsfile')
            }
        }
    }
}