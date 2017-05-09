
import secodingchallenge.*

class BootStrap {

    def init = { servletContext ->

    	def bob = new Account(name: "Bob", emailAddress: "bigbob@gmail.com", balance: 200)
    	def margaret = new Account(name: "Margaret", emailAddress: "crazymaggie@gmail.com", balance: 200)
        def alan = new Account(name: "Alan", emailAddress: "alan@gmail.com", balance: 200)
    	
    	bob.save(flush: true)
    	margaret.save(flush: true)
        alan.save(flush: true)        
    }

    def destroy = {
    }
}
