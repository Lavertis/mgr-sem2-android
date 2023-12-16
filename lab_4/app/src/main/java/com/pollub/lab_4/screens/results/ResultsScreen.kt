package com.pollub.lab_4.screens.results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pollub.lab_4.ui.theme.Lab_4Theme


@Composable
fun ResultsScreen(
    attemptCount: Int,
    onLogoutButtonClicked: () -> Unit,
) {
    Column {
        Text(
            text = "Results",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Recent attempts: $attemptCount",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Play again")
        }
        Button(
            onClick = { onLogoutButtonClicked() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Logout")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    Lab_4Theme {
        ResultsScreen(5) {}
    }
}