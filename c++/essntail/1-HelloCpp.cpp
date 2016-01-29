/*
 * =====================================================================================
 *
 *       Filename:  1-HelloCpp.cpp
 *
 *    Description:  
 *
 *
 *        Version:  1.0
 *        Created:  2014年04月25日 16时07分57秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
#include <iostream>
#include <string>
using namespace std;

int main(){

    string user_frist_name;
    string user_last_name;
    cout << "Please enter your frist name: ";
    cin >> user_frist_name;
    cout << "please enter your last name: ";
    cin >> user_last_name;

    cout << "Hello , "
         << user_frist_name
         << user_last_name
         << "... and goodbye!\n";
    return 0; 
}

