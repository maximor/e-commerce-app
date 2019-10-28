package pucmm.temas.especiales.e_commerce_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import pucmm.temas.especiales.e_commerce_app.entities.User;
import pucmm.temas.especiales.e_commerce_app.utils.UserSession;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    private UserSession session;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateUI();
    }

    private void updateUI() {
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        this.mToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, R.string.open, R.string.close);

        navigationView = (NavigationView) findViewById(R.id.nav_viewe);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.menu_home);
        navigationView.setNavigationItemSelectedListener(this);

        this.mDrawerLayout.addDrawerListener(this.mToggle);
        this.mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //If there is a session then load the data
        retrieveSession();
    }

    private void retrieveSession() {
        session = new UserSession(this);
        session.checkLogin();
        user = session.getUserInformation();

        //set user information to the drawer header
        View headerView = (View) navigationView.getHeaderView(0);
        TextView txtUserInfo = (TextView) headerView.findViewById(R.id.txtUserInfo);
        txtUserInfo.setText(user.getName());
        TextView txtEmail = (TextView) headerView.findViewById(R.id.txtUserEmail);
        txtEmail.setText(user.getEmail());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_home:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.menu_products:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new ProductFragment()).commit();
                break;
            case R.id.menu_category:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new CategoryFragment()).commit();
                break;
            case R.id.menu_tracking:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new TrackingFragment()).commit();
                break;
            case R.id.menu_profile:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.menu_signout:
                session.logoutUser();
                break;
            default:
                navigationView.setCheckedItem(R.id.menu_home);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(this.mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
