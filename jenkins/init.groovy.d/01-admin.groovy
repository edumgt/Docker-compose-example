import jenkins.model.*
import hudson.security.*

def instance = Jenkins.get()

// 이미 보안 설정이 되어 있으면 건너뜀
if (instance.getSecurityRealm() instanceof HudsonPrivateSecurityRealm) {
  println("-- Security realm already configured. Skip.")
  return
}

def adminUser = System.getenv("JENKINS_ADMIN_ID") ?: "admin"
def adminPass = System.getenv("JENKINS_ADMIN_PASSWORD") ?: "admin1234"

def realm = new HudsonPrivateSecurityRealm(false)
realm.createAccount(adminUser, adminPass)
instance.setSecurityRealm(realm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
println("-- Created admin user: ${adminUser}")
