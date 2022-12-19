def label = 'label'

node(label) {
    try {
        stage('Checkout SCM') {

        }
        pipeline()
    }
    catch (e) {
        println "Pipeline failed. Error: \n" ${e}
    }
    finally {
        echo "Clean workspace"
    }
}

def pipeline(branch, tag, gitHash, jenkinsVar) {
    
}