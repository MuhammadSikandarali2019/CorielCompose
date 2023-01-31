package com.example.corielcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.corielcompose.ui.theme.CorielComposeTheme
import com.example.corielcompose.ui.theme.NunitoSansBold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CorielWriteScreen()
        }
    }
}

@Preview
@Composable
fun CorielWriteScreen() {
    CorielComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {

            Column(Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
//                TextInput()
                DropDownExpand()
                DropDownExpand()
                MaterialButton()
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownExpand() {

    Spacer(Modifier.height(32.dp))
    val contextForToast = LocalContext.current.applicationContext

    val listItems = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    // the box
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {

        // text field
        OutlinedTextField(value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = selectedItem) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            })
        // menu
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem({ Text(selectedOption) }, onClick = {
                    selectedItem = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                })
            }
        }
    }
}

//@Composable
//fun TextInput() {
//
//    var numberText by remember { mutableStateOf(TextFieldValue("")) }
//    // Outlined Text input field with input type number
//    // It will open the number keyboard
//    Spacer(Modifier.height(32.dp))
//    OutlinedTextField(value = numberText,
//        modifier = Modifier
//            .padding(8.dp)
//            .width(300.dp),
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//        label = { Text(text = "Item Code") },
//        placeholder = { Text(text = "33181") },
//        onValueChange = {
//            numberText = it
//        })
//}

@Composable
fun MaterialButton() {
    Spacer(Modifier.height(32.dp))
    Button(
        onClick = { /*TODO*/ }, modifier = Modifier
            .padding(8.dp)
            .width(250.dp)
    ) {
        Text(text = "Next" , fontFamily = NunitoSansBold)
    }
}
