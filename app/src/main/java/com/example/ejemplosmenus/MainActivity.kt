package com.example.ejemplosmenus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.PopupMenu

class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener{ //se hace una herencia con el PopupMenu

    private var actionMode: ActionMode? = null
    var popupMenu : PopupMenu? = null //signo de interrogación para que permite el nulo kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton1: Button = findViewById(R.id.button)
        //para poder asignar el menu contextual se le va a aplicar un metodo
        //se enlaza el compomente
        val boton2: Button = findViewById(R.id.button2)
        registerForContextMenu(boton1)
        boton2.setOnLongClickListener{
            view -> when(actionMode){
            null->{
                actionMode= this.startActionMode(actionModeCallBack)
                view.isSelected = true
                true
            }
            else-> false

        }

        }

    }

    fun mostrarMenu(v: View){
        //para el tercer menu
        if(popupMenu== null){
            popupMenu=PopupMenu(this, v) //Aquí me llevo el contexto y la vista
            val inflater: MenuInflater = popupMenu!!.menuInflater // con el !! indico que tiene algo direfetente a null
            inflater.inflate(R.menu.menu_popup,popupMenu!!.menu)// aquí solo se infla el menu
            popupMenu!!.setOnMenuItemClickListener(this) // con el this digo que lo quiero controlar con esta clase

        }
        popupMenu!!.show()

    }
    //Esta es una interfaz
    private val actionModeCallBack = object : ActionMode.Callback{
        //Aquí es la creación
        override fun onCreateActionMode(mode: ActionMode, menu: Menu?): Boolean {
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.menu_action,menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false
        //cuando muestro algo


        //cuando se hace un click
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean =
            when(item.itemId){
                R.id.m_action_opl->{
                    Log.e("MenuAction", "Presionó 1")
                    true

                }
                R.id.m_action_op2->{
                    Log.e("MenuAction", "Presionó 2")
                    true

                }
                R.id.m_action_op3->{
                    Log.e("MenuAction", "Presionó 3")
                    true

                }
                else-> false
            }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
        }


    }
    override fun onContextItemSelected(item: MenuItem): Boolean =
        when (item.itemId){
            R.id.m_contextual_opl->{
                Log.e("MENUCONTEXT", "se presionó 1")
                true
            }
            R.id.m_contextual_op2->{
                Log.e("MENUCONTEXT", "se presionó 2")
                true
            }
            R.id.m_contextual_op3->{
                Log.e("MENUCONTEXT", "se presionó 3")
                true
            }
            else-> super.onContextItemSelected(item)

        }

    // el menú contextual es para toda la actividad

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_contextual, menu)
        //aunque ya tengo creado i menú, para poder trabajarlo o manipularlos este menu se adapta a una vista.
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onMenuItemClick(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.menu_popup_opl->{
                //Aquí solamente es el click
                item.isChecked = !item.isChecked
                Log.e("MenuPopup", "se presiono 1")
                true;

            }
            R.id.menu_popup_op2->{
                //Aquí solamente es el click
                item.isChecked = !item.isChecked
                Log.e("MenuPopup", "se presiono 2")
                true;

            }
            else-> false

        }
}