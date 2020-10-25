package co.plano

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import co.plano.ui.fragment.*
import kotlinx.android.synthetic.main.app_bar_act_dashboard.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        val content = findViewById<View>(R.id.content) as RelativeLayout

        val actionBarDrawerToggle: ActionBarDrawerToggle =
            object : ActionBarDrawerToggle(
                this, drawerLayout,
                R.string.open,
                R.string.close
            ) {
                private val scaleFactor = 6f
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    val slideX = drawerView.width * slideOffset
                    content.translationX = slideX
                    content.scaleX = 1 - (slideOffset / scaleFactor)
                    content.scaleY = 1 - (slideOffset / scaleFactor)
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    content.setBackgroundColor(getResources().getColor(R.color.transparent))
                }

                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    content.setBackgroundColor(getResources().getColor(R.color.white))

                }
            }
        drawerLayout.setScrimColor(Color.TRANSPARENT)
        drawerLayout.setDrawerElevation(0f)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        iv_menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        bottomNavigationMenuSelection(HomeFragment())

        iv_home_unselected.setOnClickListener {
            bottomNavigationMenuSelection(HomeFragment())
        }
        iv_eye_unselected.setOnClickListener {
            bottomNavigationMenuSelection(EyeFragment())
        }
        iv_shop_unselected.setOnClickListener {
            bottomNavigationMenuSelection(ShopFragment())
        }
        iv_gift_unselected.setOnClickListener {
            bottomNavigationMenuSelection(GiftFragment())
        }
        iv_note_unselected.setOnClickListener {
            bottomNavigationMenuSelection(NoteFragment())
        }


    }

    private fun bottomNavigationMenuSelection(fragment: Fragment?) {
        //initially all INVISIBLE
        iv_home_unselected.visibility = View.VISIBLE
        iv_eye_unselected.visibility = View.VISIBLE
        iv_shop_unselected.visibility = View.VISIBLE
        iv_gift_unselected.visibility = View.VISIBLE
        iv_note_unselected.visibility = View.VISIBLE

        rl_home_selected.visibility = View.INVISIBLE
        rl_eye_selected.visibility = View.INVISIBLE
        rl_shop_selected.visibility = View.INVISIBLE
        rl_gift_selected.visibility = View.INVISIBLE
        rl_note_selected.visibility = View.INVISIBLE

        //setting selected option
        if (fragment?.javaClass!!.name == HomeFragment().javaClass.name) {
            iv_home_unselected.visibility = View.INVISIBLE
            rl_home_selected.visibility = View.VISIBLE
            addFragment(fragment)

        } else if (fragment.javaClass.name == EyeFragment().javaClass.name) {
            iv_eye_unselected.visibility = View.INVISIBLE
            rl_eye_selected.visibility = View.VISIBLE
        } else if (fragment.javaClass.name == ShopFragment().javaClass.name) {
            iv_shop_unselected.visibility = View.INVISIBLE
            rl_shop_selected.visibility = View.VISIBLE
        } else if (fragment.javaClass.name == GiftFragment().javaClass.name) {
            iv_gift_unselected.visibility = View.INVISIBLE
            rl_gift_selected.visibility = View.VISIBLE
        } else if (fragment.javaClass.name == NoteFragment().javaClass.name) {
            iv_note_unselected.visibility = View.INVISIBLE
            rl_note_selected.visibility = View.VISIBLE
        }
    }

    protected fun addFragment(
        fragment: Fragment
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment, fragment.javaClass.name)
            .disallowAddToBackStack()
            .commit()
    }

}