app.service("apiHandler",function ($http){
    this.callPost=(url,data,onSuccess,onError)=>{
        url = "/api/"+url;
        $http.post(url,data).then((response)=>{
            if (response != null && response.data !=null){
                let result = response.data;
                if (result.status == "SUCCESS"){
                    onSuccess(result);
                }else if(result.hasError){
                    alert(result.message);
                }else {
                    alert("anknowne error!");
                }
            }
        },(err)=>{
            alert("exception");
            onError(err);
        });
    }

    this.callGet=(url,onSuccess,onError)=>{
        url = "/api/"+url;
        $http.get(url).then((response)=>{
            if (response != null && response.date !=null){
                let result = response.data;
                if (result.status == "SUCCESS"){
                    onSuccess(result);
                }else if(result.hasError){
                    alert(result.message);
                }else {
                    alert("anknown error!")
                }
            }
        },(err)=>{
            alert("exception");
            onError(err);
        });
    }

    this.callPut=(url,data,onSuccess,onError)=>{
        url = "/api/"+url;
        $http.put(url,data).then((response)=>{
            if (response != null && response.date !=null){
                let result = response.data;
                if (result.status == "SUCCESS"){
                    onSuccess(result);
                }else if(result.hasError){
                    alert(result.message);
                }else {
                    alert("anknown error!")
                }
            }
        },(err)=>{
            alert("exception");
            onError(err);
        });
    }

    this.callDelete=(url,onSuccess,onError)=>{
        url = "/api/"+url;
        $http.delete(url).then((response)=>{
            if (response != null && response.date !=null){
                let result = response.data;
                if (result.status == "SUCCESS"){
                    onSuccess(result);
                }else if(result.hasError){
                    alert(result.message);
                }else {
                    alert("anknown error!")
                }
            }
        },(err)=>{
            alert("exception");
            onError(err);
        });
    }
});