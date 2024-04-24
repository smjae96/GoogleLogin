/**
 * 구글 로그인 연동 기능
 */
 const CLIENT_ID = '381284247388-2t78uor77q58dl3f7cr9e877khd8f9he.apps.googleusercontent.com'
 const REDIRECT_URL = 'http://localhost:8080/login/oauth/google'
  window.onload = () => {
        	
        	 document.querySelector("#btn-glogin").onclick = () => {
        	        
        	        const google_login_url = 'https://accounts.google.com/o/oauth2/v2/auth'
        	                                + '?client_id=' + CLIENT_ID
        	                                + '&redirect_uri=' + REDIRECT_URL
        	                                + '&response_type=code'
        	                                + '&scope=email profile';
					location.href = google_login_url;
        			
        	}
        	
            const menuList = document.querySelectorAll("nav div");
            menuList.forEach((menu)=>{              
                menu.addEventListener('click', (ev)=>{           
                    removeActiveClass(menuList);         
                    ev.target.classList.add("active");
                });
            })
            
        }
        const removeActiveClass = (list) => {
            list.forEach((menu)=>{
                menu.classList.remove("active");
            })
        }
 