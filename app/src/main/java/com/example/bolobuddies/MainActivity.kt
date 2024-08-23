package com.example.bolobuddies

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.LiveroomActivity
import com.example.bolobuddies.ui.theme.BoloBuddiesTheme

import java.util.Random
import kotlin.text.StringBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoloBuddiesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()

                }
            }
        }
    }
}
@Composable
fun Login(){
    var roomId by remember{
        mutableStateOf("")
    }
    var username by remember{
        mutableStateOf("")
    }
    var context= LocalContext.current
    Column(modifier = Modifier.fillMaxSize().background(color = Color.White),
        Arrangement.Center,Alignment.CenterHorizontally,){
        Image(painter = painterResource(id = R.drawable.speak), contentDescription =null,
            modifier=Modifier.size(150.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "GupShup", fontSize = 50.sp, fontFamily = FontFamily.Monospace, color = Color.Magenta, fontWeight = FontWeight.Bold)
       Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = roomId, onValueChange = {roomId=it}, label = {Text(text = "Room Id")} )
        OutlinedTextField(value = username, onValueChange = {username=it}, label = {Text(text = "Username")} )
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(onClick = {roomId= GenerateRoomId()
            val intent= Intent(context,LiveroomActivity::class.java)
            intent.putExtra("userId",username)
            intent.putExtra("roomId",roomId)
            intent.putExtra("isHost",true)
            context.startActivity(intent)
        }) {
            Text("Create", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            
        }
        ElevatedButton(onClick = {
            val intent= Intent(context,LiveroomActivity::class.java)
            intent.putExtra("userId",username)
            intent.putExtra("roomId",roomId)
            intent.putExtra("isHost",false)
            context.startActivity(intent)
        },) {
            Text("Join ", fontWeight = FontWeight.Bold, fontSize = 16.sp)

        }
    }
}

fun GenerateRoomId():String{
    var id=StringBuilder()
    while(id.length<5){
        var random=Random().nextInt(10)
        id.append(random)
    }
    return id.toString()


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

        BoloBuddiesTheme {
            Surface {
                Login()
            }}
}