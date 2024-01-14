import React, { useEffect, useState } from 'react'

const Fetch = () => {

    const [url, setURL] = useState("");
    const [loading , setLoading] = useState(true);
    const [display, setDisplay ] = useState("here");
    
    const handleChange = (e)=>{

        setURL(e.target.value);
        setDisplay("");
    }

    const ensureHttps = (url)=>{
        if(!url.includes("https://")){
            url  = "https://" + url;
        }
        return url;
    }

    const formattedUrl = ensureHttps(url);


    const fetchdata = async () => {
        setLoading(true);
        try {
            const response = await fetch(`http://localhost:8080/api/v1/check?url=${formattedUrl}`);
            const data = await response.text();
            setDisplay(data);
            console.log(data);
        } catch (error) {
            console.error("Error fetching data:", error);
        }
    }
    


  return (
        <>
                <form >
                <input onChange={handleChange} type="text" placeholder='Type url here'/> 
                    <button onClick= {(e)=>{e.preventDefault() ; fetchdata()}}type="submit">Submit here </button>
                </form>
                <h1>{display}</h1>
                
                
        </>
  )
}

export default Fetch