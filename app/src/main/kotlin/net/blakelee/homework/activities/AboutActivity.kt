package net.blakelee.homework.activities

import android.app.Activity
import android.os.Bundle
import net.blakelee.homework.views.AboutUI
import org.jetbrains.anko.setContentView


class AboutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AboutUI().setContentView(this)
    }
}