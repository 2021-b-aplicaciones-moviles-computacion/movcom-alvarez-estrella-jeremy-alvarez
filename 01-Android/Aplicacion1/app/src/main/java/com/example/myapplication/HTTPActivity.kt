package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import javax.xml.transform.Result

class HTTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_httpactivity)
    }

    fun metodoGet(){
        val identificadorPost = 1
        val url = "https://jsonplaceholder.typicode.com/posts/"+identificadorPost.toString()
        url.httpGet().responseString{
            req,res,result->
            when(result){
                is Result.Failure ->{
                    val error = result.getException()
                    Log.i("http-klaxon","Error: ${error}")
                }
                is Result.Success ->{
                    val respuestaString = result.get()
                    Log.i("http-klaxon", "${respuestaString}")

                    Log.i("http-klaxon","${post?.body}")
                }
            }
        }
    }

    fun metodoPost(){
        val parametros: List<Pair<String, *>> = listOf(
            "title" to "Titulo moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )
        val url = "http://jsonplaceholder.typicode.com/posts"
        url.httpPost(parametros)
            .responseString{ req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("http-klaxon","Error: ${error}")
                    }
                    is Result.Success ->{
                        val postString = result.get()
                        Log.i("http-klaxon","${postString}")
                        val post = klaxon()
                            .parse<IPostHttp>(postString)
                        Log.i("http-klaxon","${post?.title}")
                    }
                }
            }
    }
}